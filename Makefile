
DOCKER=docker run --rm -v $(shell pwd):/home/jovyan s22s/rasterframes-notebooks
SPHINX=python3 -m sphinx
NB_EVAL=jupyter nbconvert --ExecutePreprocessor.timeout=-1 --to notebook --inplace --execute

SRC=.
SITE_DEST=target/site

PY_NOTEBOOKS=$(shell find ./Python/Minis -name '*.ipynb'| grep -v '/UF-')

.PHONY: sources
sources:
	@for s in $(PY_NOTEBOOKS); do echo $$s; done

.PHONY: eval
eval:
	$(DOCKER) $(NB_EVAL) $(PY_NOTEBOOKS)

.PHONY: quick
quick:
	$(DOCKER) $(SPHINX) $(SRC) $(SITE_DEST)

.PHONY: full
full:
	$(DOCKER) $(SPHINX) -D nbsphinx_execute=always $(SRC) $(SITE_DEST)

.PHONY: clean
clean: 
	rm -r $(DEST) .ipy* 2> /dev/null || true
	rm -r $(shell find . -name '.ipynb_checkpoints') >/ /dev/null || true
