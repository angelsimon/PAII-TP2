package com.example.tp2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream os) throws IOException {
            super(os);
        }
        protected void writeStreamHeader() throws IOException {
            reset();
        }
}

