FROM python:3
EXPOSE 8000
CMD docker run --rm -v $PWD:/home/jovyan s22s/rasterframes-notebooks ./build_docs all
COPY target/site /site
WORKDIR /site
CMD python3 -m http.server
