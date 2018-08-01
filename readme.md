# RasterFrames-book

RasterFrames-book is a repository containing examples and tutorials for the [RasterFrames library](https://github.com/locationtech/rasterframes).
The docs cover Scala and Python, and are in notebook format, using the spylon kernel for scala and the python3 kernel for python. Before building the container, the repository must be cloned and the docker-compose file in RasterFrames must be changed to add the volume containing the cloned repo. Once the docker image is built, navigate to rasterframes/deployment/docker/jupyter. Once in this directory, `docker run --rm -v ***Volume path here*** s22s/rasterframes-notebooks ./build_docs all` will build all the docs, placing them into `_build` and `Scala/_build` in your volume. It is possible to build just the python or scala docs by specifying the language as an argument instead of all.

## Generating HTML

This project uses nbsphinx to convert .ipynb files to .html. The table of contents is located in index.rst. Files added to the table of contents will automatically be linked to and have their markdown subsections linked to as well. To build the project, launch the rasterframes notebook container and make sure that all the notebooks are linked to in index.rst. If you have just added a new file, it will need to be added to the index.rst file and then the docs will need to be built with `./build_docs`. Sections at level two within a notebook (that is, with two ## in front of the markdown section) will be converted to subsections in the table of contents. 

## Evaluating notebooks

Notebooks will automatically be evaluated every time the documentation is rebuilt. 

## Preview HTML

.html files are placed in the _build/html directory once the script is run.
