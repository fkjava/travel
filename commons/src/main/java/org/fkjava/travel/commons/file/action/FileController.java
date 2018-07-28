package org.fkjava.travel.commons.file.action;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fkjava.travel.commons.file.domain.FileInfo;
import org.fkjava.travel.commons.file.service.FileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

// 把包增加到 file-servlet.xml 文件进行扫描
@Controller
@RequestMapping("/commons/file")
public class FileController {

    private Logger log = LogManager.getLogger(FileController.class);

    @Resource
    private FileService fileService;

    // doGet
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {

        // 查询数据
        List<FileInfo> list = this.fileService.find();

        ModelAndView mav = new ModelAndView();

        // mav.setViewName("/WEB-INF/content/file/list.jsp");
        // 如果配置了【视图解析器】，会根据【前缀】、【后缀】连成一个完整的路径
        mav.setViewName("commons/file/list");
        mav.addObject("list", list);// 把数据传递给list.jsp
        return mav;
    }

    /**
     * 返回的数据结果，必须符合wangEditor的JSON格式要求
     *
     * @param file
     * @return
     */
    @PostMapping("wangEditor")
    @ResponseBody
    public WangEditorResponse wangEditorUpload(@RequestParam("image") MultipartFile file) {
        String id = fileService.save(file);

        WangEditorResponse resp = new WangEditorResponse();
        // 下载路径，是为了显示图片的内容
        resp.getData().add("/commons/file/" + id);
        return resp;
    }

    static class WangEditorResponse {
        // 错误代码，0表示没错

        private int errno;
        // 返回给wangEditor的图片文件的下载地址
        private List<String> data = new LinkedList<>();

        public int getErrno() {
            return errno;
        }

        public void setErrno(int errno) {
            this.errno = errno;
        }

        public List<String> getData() {
            return data;
        }

        public void setData(List<String> data) {
            this.data = data;
        }
    }

    // method=RequestMethod.GET表示只接收对应类型的请求
    // doPost
    @RequestMapping(method = RequestMethod.POST)
    // public String upload(@RequestParam("file") MultipartFile file) {
    @ResponseBody
    public String upload(@RequestParam("imgFile") MultipartFile file) {
        String id = fileService.save(file);
        // 如果是 redirect:/ 开头表示直接重定向到当前项目的根目录
        // redirect:/file 表示重定向到当前项目的 /file
        // return "redirect:/file";

        String result = "{\"status\": 1, \"resourceId\": \"" + id + "\"}";
        return result;
    }

    // SpringMVC可以把路径的一部分当做参数使用！
    @RequestMapping(value = "/{xx}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(//
            @PathVariable("xx") String id, // 文件id
            @RequestHeader("User-Agent") String userAgent// 浏览器版本
    ) throws UnsupportedEncodingException {
        // 根据文件的id获取文件信息，里面包括文件大小、中文文件名、文件的内容类型
        FileInfo info = this.fileService.getById(id);

        if (info == null) {
            // 404
            log.error("无法根据路径找到对应的文件信息");
            return ResponseEntity.notFound().build();
        } else {
            // 构建响应消息
            // ok() 其实就是 HTTP 200 响应
            BodyBuilder builder = ResponseEntity.ok();
            builder.contentLength(info.getFileLength());// 内容长度
            builder.contentType(//
                    MediaType.parseMediaType(info.getContentType())// 内容类型
            );
            // 获得实际的文件名，实际的开发中需要根据不同的浏览器来进行判断做不同的编码
            // 实际的浏览器类型可以通过请求头来获取
            String name = info.getName();
            name = URLEncoder.encode(name, "UTF-8");
            // 设置实际的响应文件名，告诉浏览器文件要用于【下载】、【保存】
            // 不同的浏览器，处理方式不同，要根据浏览器版本进行区别判断
            if (userAgent.indexOf("MSIE") > 0) {
                // 如果是IE，只需要用UTF-8字符集进行URL编码即可
                builder.header("Content-Disposition", "attachment; filename=" + name);
            } else {
                // 而Google、FireFox、Chrome等浏览器，则需要说明编码的字符集
                // 注意filename后面有个*号，在UTF-8后面有两个单引号！
                builder.header("Content-Disposition", "attachment; filename*=UTF-8''" + name);
            }

            // 根据实际的文件路径得到文件，并且转换为byte[]
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try ( FileInputStream in = new FileInputStream(info.getPath())) {
                // 把输入流里面的信息，读取出来转换为byte[]
                byte[] buf = new byte[1024];
                for (int count = in.read(buf); count != -1; count = in.read(buf)) {
                    out.write(buf, 0, count);
                }
                byte[] data = out.toByteArray();

                // 构建响应体
                ResponseEntity<byte[]> entity = builder.body(data);
                return entity;
            } catch (IOException e) {
                log.error("找到了对应的文件信息，但是读取文件内容失败：" + e.getLocalizedMessage(), e);
                // 文件没有找到，或者读取失败
                return ResponseEntity.notFound().build();
            }
        }
    }

    // 浏览器不能发送DELETE请求！但是在REST规范里面，删除就应该是DELETE请求！
    // 于是有两种方式通过浏览器发送DELETE请求：
    // 1.使用jQuery的ajax
    // 2.使用POST请求，增加一个隐藏的input，名字默认为 _method，值为delete，
    // 第二种方式需要增加Spring的一个过滤器才能用。
    @RequestMapping(value = "/{xx}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("xx") String id) {
        this.fileService.delete(id);
        return "OK";
    }
}
