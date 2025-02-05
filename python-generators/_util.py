import os.path
import pathlib

def java_package_dir_by_name(name:str): return os.path.join(*name.split('.'))

SUPERPOWER_IMPORTS              = (
    'jl95.lang.*',
)
SUPERPOWER_STATIC_IMPORTS       = (
    'jl95.lang.SuperPowers.*',
)
VARIADIC_CLASSES_JAVA_PACKAGE   = 'jl95.lang.variadic'
MAVEN_PROJECT_DIR               = pathlib.Path(__file__).parents[1]
JAVA_CLASSES_ROOT_DIR           = os.path.join(MAVEN_PROJECT_DIR, 'src', 'main', 'java')
JAVA_TEST_CLASSES_ROOT_DIR      = os.path.join(MAVEN_PROJECT_DIR, 'src', 'test', 'java')
VARIADIC_CLASSES_DIR            = os.path.join(JAVA_CLASSES_ROOT_DIR, java_package_dir_by_name(VARIADIC_CLASSES_JAVA_PACKAGE))
