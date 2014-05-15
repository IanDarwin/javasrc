#!/usr/bin/env R

# Histogram the data for Java Cookbook recipe "Better Random Numbers"

# Be sure you have run "Random4" in this directory before running this script

# Original by Ian Darwin, re-created by Benjamin Darwin

# BEGIN main
png("out.png")
us <- read.table("file1")[[1]]
ns <- read.table("file2")[[1]]

layout(t(c(1,2)), respect=TRUE)

hist(us, main = "Using nextRandom()", nclass = 10,
       xlab = NULL, col = "lightgray", las = 1, font.lab = 3)

hist(ns, main = "Using nextGaussian()", nclass = 16,
       xlab = NULL, col = "lightgray", las = 1, font.lab = 3)
dev.off()
# END main
