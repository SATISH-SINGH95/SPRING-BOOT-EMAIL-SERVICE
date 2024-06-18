package com.email.constants;

import java.util.Arrays;
import java.util.List;

public class EmailConstants {

    public static final String EMAIL_SENDING_WITH_ATTACHMENT_ERROR = "Error in sending email with attachment";
    public static final String EMAIL_SENDING_ERROR = "Error in sending email";

    public static class Extension{
        public static final List<String> EXTENSION_LIST = Arrays.asList(".doc", ".docx", ".png", ".jpeg", ".jpg", ".pdf", ".txt");
        public static final String EXTENSION_NOT_VALID = "Extension is not supported";

    }

}
