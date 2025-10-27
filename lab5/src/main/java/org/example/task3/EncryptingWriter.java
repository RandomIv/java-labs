package org.example.task3;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class EncryptingWriter extends FilterWriter {

    private final int keyCode;

    public EncryptingWriter(Writer out, int keyCode) {
        super(out);
        this.keyCode = keyCode;
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
