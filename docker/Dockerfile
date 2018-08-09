FROM s22s/rasterframes-notebooks
EXPOSE 8000
USER root
WORKDIR rasterframes-book
RUN chown -R $NB_USER .
USER $NB_USER
RUN ./build_docs all
WORKDIR target/site
CMD python3 -m http.server
