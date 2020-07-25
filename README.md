# File Type Analyzer

A tool that extracts file's type info (using Knuth-Morris-Pratt algorithm) to determine the type of the file.

Program accepts file type patterns and a path to a folder with files and matches the pattern against some file.

Program is organized as several workers. 
Each worker is equivalent (logically) to the single-threaded pattern matcher: 
it takes several files and matches them consequently using the searching algorithm. 
The answers of each worker will be aggregated as the total execution result.

#### Output example
```
java Main test_files patterns.db
doc_0.doc: MS Office Word 2003
doc_1.pptx: MS Office PowerPoint 2007+
doc_2.pdf: PDF document
file.pem: PEM certificate
```
