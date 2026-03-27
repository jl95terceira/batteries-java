import os.path
import pathlib

def java_package_dir_by_name(name:str): return os.path.join(*name.split('.'))

IMPORTS              = (
    'jl95.lang.*',
    'jl95.util.DataClass',
)
STATIC_IMPORTS       = (
    'jl95.lang.SuperPowers.*',
)
LANG_CLASSES_JAVA_PACKAGE       = 'jl95.lang'
VARIADIC_CLASSES_JAVA_PACKAGE   = f'{LANG_CLASSES_JAVA_PACKAGE}.variadic'
PRIMITIVE_CLASSES_JAVA_PACKAGE  = f'{LANG_CLASSES_JAVA_PACKAGE}.primitive'
MAVEN_PROJECT_DIR               = pathlib.Path(__file__).parents[1]
JAVA_CLASSES_ROOT_DIR           = os.path.join(MAVEN_PROJECT_DIR, 'src', 'main', 'java')
JAVA_TEST_CLASSES_ROOT_DIR      = os.path.join(MAVEN_PROJECT_DIR, 'src', 'test', 'java')
VARIADIC_CLASSES_DIR            = os.path.join(JAVA_CLASSES_ROOT_DIR, java_package_dir_by_name(VARIADIC_CLASSES_JAVA_PACKAGE))
LANG_CLASSES_DIR                = os.path.join(JAVA_CLASSES_ROOT_DIR, java_package_dir_by_name(LANG_CLASSES_JAVA_PACKAGE))
PRIMITIVE_CLASSES_DIR           = os.path.join(JAVA_CLASSES_ROOT_DIR, java_package_dir_by_name(PRIMITIVE_CLASSES_JAVA_PACKAGE))
