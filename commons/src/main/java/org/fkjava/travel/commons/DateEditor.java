package org.fkjava.travel.commons;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 *
 * @author LuoWenqiang
 */
public class DateEditor extends PropertyEditorSupport {

    private DateFormat dateFormat;
    private boolean allowEmpty = true;

    public DateEditor() {
    }

    public DateEditor(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DateEditor(DateFormat dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
    }

    /**
     * Parse the Date from the given text, using the specified DateFormat.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            // Treat empty String as null value.  
            setValue(null);
        } else {
            try {
                if (this.dateFormat != null) {
                    setValue(this.dateFormat.parse(text));
                } else if (text.contains(":")) {
                    setValue(DateUtils.toDateTime(text));
                } else {
                    setValue(DateUtils.toDate(text));
                }
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }

    /**
     * Format the Date as String, using the specified DateFormat.
     *
     * @return
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        DateFormat format = this.dateFormat;
        if (format == null) {
            return DateUtils.dataTimeToString(value);
        }
        return (value != null ? format.format(value) : "");
    }
}
