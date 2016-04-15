README: Webcrawler

This program checks the "breadth" of a site, i.e. the maximum distance between two web pages within a given section of the internet. I implemented it using a stack of Vectors, each vector being a series of websites visited so far. 

I've tested it a couple times and i'm pretty sure it works. The catch is that even on seemingly small websites, like the CSCI 136 homepage, this program can take a while (especially since it has to check ALL of the links on the Javadocs for structure 5). To get around this, I put a few extra restrictions when pushing new vectors onto the stack. ITs not terribly elegant, but it was one way to make sure the code works. 