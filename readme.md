# RasterFrames-book

RasterFrames-book is a repository containing examples and tutorials for the [RasterFrames library](https://github.com/locationtech/rasterframes).
The docs cover Scala and Python, and are in notebook format, using the spylon kernel for scala and the python3 kernel for python.

## Generating HTML

This project uses nbsphinx to convert .ipynb files to .html. The table of contents is located in index.rst. Files added to the table of contents will automatically be linked to and have their markdown subsections linked to as well. To build the project, launch the rasterframes notebook container and make sure that all the notebooks are linked to in index.rst. If you have just added a new file, it will need to be added to the index.rst file. Once all files are added, navigate to the top level directory and run the command `python3 -m sphinx . _build`.

## Evaluating notebooks

Notebooks will automatically be evaluated every time the documentation is rebuilt. 

## Preview HTML

.html files are placed in the _build/html directory once $make html is run.
