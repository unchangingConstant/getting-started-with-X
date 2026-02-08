package com.github.unchangingconstant.simpleserver;

import com.github.unchangingconstant.simpleserver.utils.Server;

import java.io.IOException;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {
        System.out.println(Arrays.toString(args));
        Server.runServer(Integer.parseInt(args[0]));
    }
}
