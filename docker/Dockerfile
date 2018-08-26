FROM s22s/rasterframes-notebooks:latest
EXPOSE 8000
USER root
WORKDIR rasterframes-book
RUN chown -R $NB_USER .
USER $NB_USER
RUN git pull origin master
RUN ./build_docs all eval
WORKDIR target/site
CMD python3 -m http.server
