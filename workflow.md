# rasterframes-book workflow

The easiest way to make changes to rasterframes-book is to clone rasterframes and then build the jupyter docker container by running sbt deployment/rfNotebookContainer from the rasterframes working directory. Once that notebook is built, navigate to rasterframes/deployment/docker/jupyter and run docker-compose up. The container will automatically clone the rasterframes-book repo Connect to the url presented in the shell. Once there, make the necessary changes and then commit them by opening up a new terminal from the "new" menu in the top right corner. Commit and push the changes like any other git project (you will have to enter your email and username by using git config). Images or other media on your computer can be inserted by being in a markdown cell and selecting edit > insert image. The html from build_docs can be previewed by going to the terminal and running ./build_docs. The corresponding html will be placed in target/site and can be viewed from the notebook by just opening one of the html files from within the notebook. Once the notebooks are ready to be run, commit changes and close the notebook. Then, go to the computer responsible for hosting the files and clone the rasterframes-book repo and build the docker image in the repo. This will automatically build the html and when the container is run with the -p option, mapping whatever local port to port 8000 (the one exposed in the docker container) the container will be ready to serve the html.

Tips and tricks:

* Markdown images won't render properly in sphinx if they have no markdown flavor text.
* Edit the css in astraea_theme > static > pyramid.css_t
* Notebooks that begin with UF (for unfinished) will be ignored by nbsphinx.
* There are two levels of index files, one for the python/scala divide and one for each language. The index files contain primarily info about the table of contents, and the files that will be included in the project.
* There is currently no good way to insert hidden html or delete only the inputs of cells.
* The first person plural or second person singular should be used throughout the notebooks.
* If html is used, nbsphinx will not automatically copy the images to target, and they will have to be manually inserted
