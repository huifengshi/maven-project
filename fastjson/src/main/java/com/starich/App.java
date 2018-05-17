package com.starich;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteDateUseDateFormat.getMask();
        ClassA a = new ClassA();
        a.setDateOriginal(new Date());
        a.setDateWithFormat(new Date());

        System.out.print(JSON.toJSON(a));
    }


    public static class ClassA {
        private Date dateOriginal;
        @JSONField(format = "yyyy-MM-dd")
        private Date  dateWithFormat;

        public Date getDateOriginal() {
            return dateOriginal;
        }

        public void setDateOriginal(Date dateOriginal) {
            this.dateOriginal = dateOriginal;
        }

        public Date getDateWithFormat() {
            return dateWithFormat;
        }

        public void setDateWithFormat(Date dateWithFormat) {
            this.dateWithFormat = dateWithFormat;
        }
    }
}
