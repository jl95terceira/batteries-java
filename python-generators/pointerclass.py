import os
import typing

from _util import *

def main():

    import os

    os.makedirs(VARIADIC_CLASSES_DIR, exist_ok=True)
    static_code:str = ''
    with open("pointerclass.template.txt", 'r') as template_file:

        class_code_template:str = template_file.read()

    with open("pointerclass.applier.template.txt", 'r') as applier_template_file:

        applier_class_code_template:str = applier_template_file.read()

    for (clas_name,clas_repr,value_type,is_generic,applier_repr,clas_pkg,clas_dir) in (
        ("Pointer"       ,"Pointer<T>"    , "T"      , True , "Applier<T>", LANG_CLASSES_JAVA_PACKAGE, LANG_CLASSES_DIR), 
        ("IntPointer"    ,"IntPointer"    , "int"    , False, "Applier"   , PRIMITIVE_CLASSES_JAVA_PACKAGE, PRIMITIVE_CLASSES_DIR),
        ("LongPointer"   ,"LongPointer"   , "long"   , False, "Applier"   , PRIMITIVE_CLASSES_JAVA_PACKAGE, PRIMITIVE_CLASSES_DIR), 
        ("FloatPointer"  ,"FloatPointer"  , "float"  , False, "Applier"   , PRIMITIVE_CLASSES_JAVA_PACKAGE, PRIMITIVE_CLASSES_DIR), 
        ("DoublePointer" ,"DoublePointer" , "double" , False, "Applier"   , PRIMITIVE_CLASSES_JAVA_PACKAGE, PRIMITIVE_CLASSES_DIR), 
        ("BooleanPointer","BooleanPointer", "boolean", False, "Applier"   , PRIMITIVE_CLASSES_JAVA_PACKAGE, PRIMITIVE_CLASSES_DIR), 
        ("CharPointer"   ,"CharPointer"   , "char"   , False, "Applier"   , PRIMITIVE_CLASSES_JAVA_PACKAGE, PRIMITIVE_CLASSES_DIR), 
        ("BytePointer"   ,"BytePointer"   , "byte"   , False, "Applier"   , PRIMITIVE_CLASSES_JAVA_PACKAGE, PRIMITIVE_CLASSES_DIR)
    ,):

        class_code     :str = class_code_template.format(**{
            "clas_pkg"  : clas_pkg,
            "clas_repr" : clas_repr,
            "clas_name" : clas_name,
            "value_type": value_type,
            "value_type_description": f"type {value_type}" if not is_generic else "generic type",
            "extra_javadoc"         : "\n * @param <T> value type" if is_generic else "",
            "applier_function"      : applier_class_code_template.format(**{
                "clas_repr" : applier_repr,
                "value_type": value_type,
            }),
            "applier_function_repr" : applier_repr,
        })
        os.makedirs(clas_dir, exist_ok=True)
        with open(f'{os.path.join(clas_dir,clas_name)}.java', 'w') as fd:

            fd.write(class_code)

        # static_code       += '    /**\n     * tuple of {0} elements\n{1}\n     * @return tuple\n     */\n'.format(i, '\n'.join(map(lambda j: '     * @param <{1}> type {0}\n     * @param {2} element {0}'.format(1+j, ARG_TYPE(j), ARG_NAME(j)), range(i))))
        # static_code       += '    public static {0} {1} T({2}) {{ return new {3}( {4}); }}\n'.format(CLASS_GENERICS(i), CLASS_TYPE(i), ', '.join(map(ARG, range(i))), CLASS_NAME(i)+('<>' if i > 0 else ''), ', '.join(map(ARG_NAME, range(i))))

#    with open('{d}\\{fn}'.format(d=VARIADIC_CLASSES_DIR, fn='_Static.java'), 'w') as fd:

#        fd.write(static_code)
        
    # TO-DO: embed pp(java) source in SuperPowers to generate helper static methods

if __name__ == '__main__':

    import argparse
    p = argparse.ArgumentParser(description='Generate Java Pointer classes for generic types and for primitive types')
    p.parse_args()
    main()