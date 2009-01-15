/*
 * JBoss DNA (http://www.jboss.org/dna)
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * See the AUTHORS.txt file in the distribution for a full listing of 
 * individual contributors. 
 *
 * JBoss DNA is free software. Unless otherwise indicated, all code in JBoss DNA
 * is licensed to you under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * JBoss DNA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.dna.common.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * @author Randall Hauch
 */
public class IoUtil {

    /**
     * Read and return the entire contents of the supplied {@link InputStream stream}. This method always closes the stream when
     * finished reading.
     * 
     * @param stream the stream to the contents; may be null
     * @return the contents, or an empty byte array if the supplied reader is null
     * @throws IOException if there is an error reading the content
     */
    public static byte[] readBytes( InputStream stream ) throws IOException {
        if (stream == null) return new byte[] {};
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        boolean error = false;
        try {
            int numRead = 0;
            while ((numRead = stream.read(buffer)) > -1) {
                output.write(buffer, 0, numRead);
            }
        } catch (IOException e) {
            error = true; // this error should be thrown, even if there is an error closing stream
            throw e;
        } catch (RuntimeException e) {
            error = true; // this error should be thrown, even if there is an error closing stream
            throw e;
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                if (!error) throw e;
            }
        }
        output.flush();
        return output.toByteArray();
    }

    /**
     * Read and return the entire contents of the supplied {@link File file}.
     * 
     * @param file the file containing the contents; may be null
     * @return the contents, or an empty byte array if the supplied file is null
     * @throws IOException if there is an error reading the content
     */
    public static byte[] readBytes( File file ) throws IOException {
        if (file == null) return new byte[] {};
        InputStream stream = new BufferedInputStream(new FileInputStream(file));
        boolean error = false;
        try {
            return readBytes(stream);
        } catch (IOException e) {
            error = true; // this error should be thrown, even if there is an error closing stream
            throw e;
        } catch (RuntimeException e) {
            error = true; // this error should be thrown, even if there is an error closing stream
            throw e;
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                if (!error) throw e;
            }
        }
    }

    /**
     * Read and return the entire contents of the supplied {@link Reader}. This method always closes the reader when finished
     * reading.
     * 
     * @param reader the reader of the contents; may be null
     * @return the contents, or an empty string if the supplied reader is null
     * @throws IOException if there is an error reading the content
     */
    public static String read( Reader reader ) throws IOException {
        if (reader == null) return "";
        char[] buffer = new char[1024];
        StringBuilder sb = new StringBuilder();
        boolean error = false;
        try {
            int numRead = 0;
            while ((numRead = reader.read(buffer)) > -1) {
                sb.append(buffer, 0, numRead);
            }
        } catch (IOException e) {
            error = true; // this error should be thrown, even if there is an error closing reader
            throw e;
        } catch (RuntimeException e) {
            error = true; // this error should be thrown, even if there is an error closing reader
            throw e;
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                if (!error) throw e;
            }
        }
        return sb.toString();
    }

    /**
     * Read and return the entire contents of the supplied {@link InputStream}. This method always closes the stream when finished
     * reading.
     * 
     * @param stream the streamed contents; may be null
     * @return the contents, or an empty string if the supplied stream is null
     * @throws IOException if there is an error reading the content
     */
    public static String read( InputStream stream ) throws IOException {
        return stream == null ? "" : read(new InputStreamReader(stream));
    }

    /**
     * Write the entire contents of the supplied string to the given stream. This method always flushes and closes the stream when
     * finished.
     * 
     * @param content the content to write to the stream; may be null
     * @param stream the stream to which the content is to be written
     * @throws IOException
     * @throws IllegalArgumentException if the stream is null
     */
    public static void write( String content,
                              OutputStream stream ) throws IOException {
        CheckArg.isNotNull(stream, "destination stream");
        boolean error = false;
        try {
            if (content != null) {
                byte[] bytes = content.getBytes();
                stream.write(bytes, 0, bytes.length);
            }
        } catch (IOException e) {
            error = true; // this error should be thrown, even if there is an error flushing/closing stream
            throw e;
        } catch (RuntimeException e) {
            error = true; // this error should be thrown, even if there is an error flushing/closing stream
            throw e;
        } finally {
            try {
                stream.flush();
            } catch (IOException e) {
                if (!error) throw e;
            } finally {
                try {
                    stream.close();
                } catch (IOException e) {
                    if (!error) throw e;
                }
            }
        }
    }

    /**
     * Write the entire contents of the supplied string to the given writer. This method always flushes and closes the writer when
     * finished.
     * 
     * @param content the content to write to the writer; may be null
     * @param writer the writer to which the content is to be written
     * @throws IOException
     * @throws IllegalArgumentException if the writer is null
     */
    public static void write( String content,
                              Writer writer ) throws IOException {
        CheckArg.isNotNull(writer, "destination writer");
        boolean error = false;
        try {
            if (content != null) {
                writer.write(content);
            }
        } catch (IOException e) {
            error = true; // this error should be thrown, even if there is an error flushing/closing writer
            throw e;
        } catch (RuntimeException e) {
            error = true; // this error should be thrown, even if there is an error flushing/closing writer
            throw e;
        } finally {
            try {
                writer.flush();
            } catch (IOException e) {
                if (!error) throw e;
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    if (!error) throw e;
                }
            }
        }
    }

    /**
     * Write the entire contents of the supplied string to the given stream. This method always flushes and closes the stream when
     * finished.
     * 
     * @param input the content to write to the stream; may be null
     * @param stream the stream to which the content is to be written
     * @throws IOException
     * @throws IllegalArgumentException if the stream is null
     */
    public static void write( InputStream input,
                              OutputStream stream ) throws IOException {
        CheckArg.isNotNull(stream, "destination stream");
        boolean error = false;
        try {
            if (input != null) {
                byte[] buffer = new byte[1024];
                try {
                    int numRead = 0;
                    while ((numRead = input.read(buffer)) > -1) {
                        stream.write(buffer, 0, numRead);
                    }
                } finally {
                    input.close();
                }
            }
        } catch (IOException e) {
            error = true; // this error should be thrown, even if there is an error flushing/closing stream
            throw e;
        } catch (RuntimeException e) {
            error = true; // this error should be thrown, even if there is an error flushing/closing stream
            throw e;
        } finally {
            try {
                stream.flush();
            } catch (IOException e) {
                if (!error) throw e;
            } finally {
                try {
                    stream.close();
                } catch (IOException e) {
                    if (!error) throw e;
                }
            }
        }
    }

    /**
     * Write the entire contents of the supplied string to the given writer. This method always flushes and closes the writer when
     * finished.
     * 
     * @param input the content to write to the writer; may be null
     * @param writer the writer to which the content is to be written
     * @throws IOException
     * @throws IllegalArgumentException if the writer is null
     */
    public static void write( Reader input,
                              Writer writer ) throws IOException {
        CheckArg.isNotNull(writer, "destination writer");
        boolean error = false;
        try {
            if (input != null) {
                char[] buffer = new char[1024];
                try {
                    int numRead = 0;
                    while ((numRead = input.read(buffer)) > -1) {
                        writer.write(buffer, 0, numRead);
                    }
                } finally {
                    input.close();
                }
            }
        } catch (IOException e) {
            error = true; // this error should be thrown, even if there is an error flushing/closing writer
            throw e;
        } catch (RuntimeException e) {
            error = true; // this error should be thrown, even if there is an error flushing/closing writer
            throw e;
        } finally {
            try {
                writer.flush();
            } catch (IOException e) {
                if (!error) throw e;
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    if (!error) throw e;
                }
            }
        }
    }

    private IoUtil() {
        // Prevent construction
    }
}
