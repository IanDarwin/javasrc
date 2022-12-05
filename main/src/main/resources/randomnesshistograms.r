#!/usr/bin/env R

# Histogram the data for Java Cookbook recipe "Better Random Numbers"

# Be sure you have run "Random4" in this directory before running this script

# Rewritten by Benjamin Darwin

# tag::main[]
png("randomness.png")
us <- read.table("normal.txt")[[1]]
ns <- read.table("gaussian.txt")[[1]]

layout(t(c(1,2)), respect=TRUE)

hist(us, main = "Using nextDouble()", nclass = 10,
       xlab = NULL, col = "lightgray", las = 1, font.lab = 3)

hist(ns, main = "Using nextGaussian()", nclass = 16,
       xlab = NULL, col = "lightgray", las = 1, font.lab = 3)
dev.off()
# end::main[]
