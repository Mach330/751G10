/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package org.sample;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import static java.util.Arrays.stream;

public class MyBenchmark {

    public int getRandom(){
        Random rand = new Random();

        return rand.nextInt();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public int[][] testMethod1() {

        int iValue = 20000;
        int jValue = 1000;

        int array1[][] = new int[iValue][jValue];
        int random = getRandom();

        int[][] doubled2D =

        Arrays.stream(array1)//.parallel()
                .map(x -> Arrays.stream(x)//.parallel()
                        .map(y -> {
                            y = random;
                            y = (int) Math.sqrt((y)*6);
                            return 2 * (y+1);
                        })
                        .toArray())
                .toArray(int[][]::new);
        return doubled2D;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void testMethod2() {

        int iValue = 20000;
        int jValue = 1000;

        int array1[][] = new int[iValue][jValue];
        int random = getRandom();
        int[][] doubled2D =

                Arrays.stream(array1).parallel()
                        .map(x -> Arrays.stream(x)//.parallel()
                                .map(y -> {
                                    y = random;
                                    y = (int) Math.sqrt((y)*6);
                                    return 2 * (y+1);
                                })
                                .toArray())
                        .toArray(int[][]::new);

    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void testMethod3() {

        int iValue = 20000;
        int jValue = 1000;

        int array1[][] = new int[iValue][jValue];
        int random = getRandom();
        int[][] doubled2D =

                Arrays.stream(array1)//.parallel()
                        .map(x -> Arrays.stream(x).parallel()
                                .map(y -> {
                                    y = random;
                                    y = (int) Math.sqrt((y)*6);
                                    return 2 * (y+1);
                                })
                                .toArray())
                        .toArray(int[][]::new);

    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void testMethod4() {

        int iValue = 20000;
        int jValue = 1000;

        int array1[][] = new int[iValue][jValue];
        int random = getRandom();
        int[][] doubled2D =

                Arrays.stream(array1).parallel()
                        .map(x -> Arrays.stream(x).parallel()
                                .map(y -> {
                                    y = random;
                                    y = (int) Math.sqrt((y)*6);
                                    return 2 * (y+1);
                                })
                                .toArray())
                        .toArray(int[][]::new);

    }


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(MyBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(10)
                .measurementIterations(50)

                .build();

        new Runner(opt).run();

    }
}
