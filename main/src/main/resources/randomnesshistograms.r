#!/usr/bin/env R

# Histogram the data for Java Cookbook recipe "Better Random Numbers"

# If using standard R:
# Be sure you have run "Random4" in this directory before running this script;
# the Java code writes into the two text files.
# If using 'renjin':
# Be sure renjin is on your classpath (See pom.xml)
# the Java code passes the two arrays via Renjin

# By Ian Darwin; parts rewritten by Benjamin Darwin

# tag::main[]
png("randomness.png", width = 1024, height = 700)

if (!exists("us")) {
	us <- read.table("normal.txt")[[1]]
	ns <- read.table("gaussian.txt")[[1]]
}

layout(t(c(1,2)), respect=TRUE)

hist(us, main = "Using nextDouble()", nclass = 10,
       xlab = NULL, col = "lightgray", las = 1, font.lab = 3)

hist(ns, main = "Using nextGaussian()", nclass = 16,
       xlab = NULL, col = "lightgray", las = 1, font.lab = 3)
dev.off()
# end::main[]
