Thought questions:

1. the closest we can get is: 9.704773256302701E-4
the first block configuration is 4.0 5.0 6.0 10.0 11.0 12.0 13.0
Which means the second is : 1,2,3,7,8,9,14,15

note this is closer than the solution presented in the handout.


2. using the -Xint command, the problem takes around 7.8 sec on average to complete the 20 block problem. The 21 block problem should take roughly twice as long, since there are twice as many subsets to test, and in fact it does, taking about 16.1 seconds. The 25 block problem takes around 256 sec. these and a few other experiments indicate the program conveniently takes 2^(-17+n) seconds to complete, where n is the number of blocks. It follows that, on my machine, the 50 block problem should take somewhere around 2^33 or 8589934592 seconds (272.38 years). we could optimize it so it only checks good potential subsets, which means the problem should only take about 100 years to complete. 

3. Using System.currentTime() , we could find the time before we begin checking subsets and before testing each subset. once the current time - start time is longer than 1 second, we stop. We could use java.util.Random to get a random index on the vector we're checking. since this is an optimization for larger problems, we shouldn't be concerned about randomly checking the same index twice. 