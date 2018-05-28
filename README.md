# Readme

This repo contains the tests run by group 10 for project 6, Softeng 751 2018.

The code has been compiled into 2 JARs which you will find in the `/JARs` folder.
Inside are two folders which contain the benchmarks we ran.
Both benchmarks use the following format:
- 10 warmup iterations, 100 real iterations
- 5 functions, each iterating over a 2D array with 120 Values (12x10)
- The first function uses nested for loops to demonstrate the pure sequential performance
- The next four use Java Array Streams to look at how useful parallelisation is when doing large versus small compute tasks.
- Test Method 1 uses pure sequential streams, with no parallel flags enabled.
- Test Method 2 uses a sequential stream for the inner loop but parallelises the outer loop
- Test Method 3 uses a sequential stream for the outer loop but parallelises the inner loop
- Test Method 4 parallelises both the inner and the outer loop

The small computation benchmark will iterate over the array doing some basic arithmetic on each element.
The large computation benchmark will caluclate whether a random integer is a prime number or not.

If you wish to make your own JARs by editing the functions in the `MyBenchmarks.java` file, run the following command using Maven:
`clean compile assembly:single`
And the output will be a JAR placed in `/SE751_JMH/target/` folder.

The results from the PC specified in the report can be found in the `/results/` folder. 