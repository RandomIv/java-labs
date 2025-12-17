package org.example.task3;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

public class EncryptingWriter extends FilterWriter {

    private final int keyCode;
    private static final Logger logger = Logger.getLogger(EncryptingWriter.class.getName());

    public EncryptingWriter(Writer out, int keyCode) {
        super(out);
        this.keyCode = keyCode;
        logger.fine("EncryptingWriter initialized with key code: " + keyCode);
    }

    private int encrypt(int c) {
        return c + keyCode;
    }

    @Override
    public void write(int c) throws IOException {
        super.write(encrypt(c));
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            write(cbuf[off + i]);
        }
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len);
    }
}