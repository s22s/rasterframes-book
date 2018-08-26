# RasterFrames User Manual and Tutorials

This repository contains examples and tutorials for the [RasterFrames Spark Library](https://github.com/locationtech/rasterframes). The documentation covers both Scala and Python, and are in Jupyter Notebook format. The "spylon" Jupyter kernel is used for Scala and the "python3" kernel for Python. 

## Generating HTML

To build the documentation, clone this repository, and run:

    cd rasterframes-book
    make full
    
This will build all the docs, except for those with UF in front of their name (for unfinished), placing them into `target/site` in your volume.  Running `make clean` will delete the generated site and any byproducts. `make quick` will build the site with the content of the cells as they were last saved.

## Preview HTML

To Preview the results, run:

    (cd target/site/; python3 -m http.server)

and point your browser go to [http://localhost:8000/]().

## Details

This project uses `nbsphinx` to convert `.ipynb` files to `.html`. The table of contents is located in `index.rst`. Files added to the table of contents will automatically be linked to and have their markdown subsections linked to as well. 

To build the project, launch the rasterframes notebook container and make sure that all the notebooks are linked to in `index.rst`. If you have just added a new file, it will need to be added to the `index.rst` file and then the docs will need to be built with `./build_docs`. 

Sections at level two within a notebook (that is, with two octothorps (`##`) in front of the markdown section) will be converted to subsections in the table of contents. 

## Evaluating Notebooks

If you run `make eval`, nbsphinx will run every cell in each notebook, stopping if any errors are encountered and outputting the result to html as usual. 

## Hosting the server 

Build and run the docker container specified by the Dockerfile, making sure that when it is run, the exposed port in the docker container (8000) is connected to a port outside the docker container to allow it to be accessed 

(should look like `docker run -p 8000:8000 <image-hash>`)
