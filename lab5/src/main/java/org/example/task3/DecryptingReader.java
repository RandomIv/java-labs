package org.example.task3;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class DecryptingReader extends FilterReader {

    private final int keyCode;

    public DecryptingReader(Reader in, int keyCode) {
        super(in);
        this.keyCode = keyCode;
    }

    private int decrypt(int c) {
        return c - keyCode;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return (c == -1) ? c : decrypt(c);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int n = super.read(cbuf, off, len);
        if (n == -1) return n;
        for (int i = 0; i < n; i++) {
            cbuf[off + i] = (char) decrypt(cbuf[off + i]);
        }
        return n;
    }
}
