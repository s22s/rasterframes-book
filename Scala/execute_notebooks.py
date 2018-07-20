import nbformat
from nbconvert.preprocessors import ExecutePreprocessor

"""
execute_notebook

:param dir_name: this is the directory the file is in
:param nb_name: this is the name of the actual notebook
:writes: the executed notebook to nb_outputs/
"""
def execute_notebook(dir_name, nb_name):
    
    if(dir_name[-1] != '/'):
       dir_name = (dir_name + '/')

    with open(dir_name + nb_name) as f:
        nb = nbformat.read(f, as_version=4)
    
    ep = ExecutePreprocessor(timeout=600, kernel_name='spylon-kernel')

    ep.preprocess(nb, {'metadata': {'path': dir_name}})

    with open('executed/executed-' + nb_name, 'wt') as f:
        nbformat.write(nb, f)

execute_notebook('Minis/', 'ndvi-scala.ipynb')