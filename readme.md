# RasterFrames-book

RasterFrames-book is a repository containing examples and tutorials for the [RasterFrames library](https://github.com/locationtech/rasterframes).
The docs cover Scala and Python, and are in notebook format, using the spylon kernel for scala and the python3 kernel for python.

## Generating HTML

This project uses nbsphinx to convert .ipynb files to .html. The table of contents is located in index.rst. Files added to the table of contents will automatically be linked to and have their markdown subsections linked to as well. To build the project, run the command $make html on the command line. Any cells without outputs will be evaluated, and cells with outputs will have those outputs displayed.

## Evaluating notebooks

## Preview HTML

.html files are placed in the _build/html directory once $make html is run.

## Update the version of RasterFrames
