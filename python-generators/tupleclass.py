import os
import typing

from _util import *

N           :int = 20 # inclusive
## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- 
CLASS_NAME_B:str = 'Tuple'
ARG_TYPE_B  :str = 'A'
ARG_NAME_B  :str = 'a'
SUPERPOWER  :str = 'tuple'
## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- 
ARG_TYPE        :typing.Callable[[int],str] = lambda i: '{0}{1}' .format(ARG_TYPE_B, 1+i)
ARG_NAME        :typing.Callable[[int],str] = lambda i: '{0}{1}' .format(ARG_NAME_B, 1+i)
ARG             :typing.Callable[[int],str] = lambda i: '{0} {1}'.format(ARG_TYPE(i), ARG_NAME(i))
CLASS_NAME      :typing.Callable[[int],str] = lambda n: '{0}{1}' .format(CLASS_NAME_B, n)
CLASS_GENERICS  :typing.Callable[[int],str] = lambda n: '<{0}>'  .format(', '.join(map(ARG_TYPE, range(n)))) if n > 0 else ''
CLASS_TYPE      :typing.Callable[[int],str] = lambda n: '{0}{1}' .format(CLASS_NAME(n), CLASS_GENERICS(n))
CLASS_NAME_FULL :typing.Callable[[int],str] = lambda n: '{0}.{1}'.format(VARIADIC_CLASSES_JAVA_PACKAGE, CLASS_TYPE(n))

def main():

    import os

    os.makedirs(VARIADIC_CLASSES_DIR, exist_ok=True)
    static_code:str = ''
    for i in range(1+N):

        class_code     :str = ''
        class_code         += 'package {0};\n\n'.format(VARIADIC_CLASSES_JAVA_PACKAGE)
        for STATIC_IMPORT in SUPERPOWER_STATIC_IMPORTS:

            class_code += f'import static {STATIC_IMPORT};\n'

        for IMPORT in SUPERPOWER_IMPORTS:

            class_code += f'import {IMPORT};\n\n'

        class_code         += '/**\n'
        class_code         += ' * Tuple with {0} elements.\n'.format(i)
        for j in range(i):
        
            class_code     += ' * @param <{1}> type {0}'.format(1+j, ARG_TYPE(j)) + '\n'

        class_code         += ' */\n'
        class_code         += 'public class {0} extends DataClass {{\n'.format(CLASS_TYPE(i))
        for j in range(i):

            class_code     += '\n    /**\n     * element {0}\n     */'.format(1+j)
            class_code     += '\n    public final {0};'.format(ARG(j))

        class_code         += '\n    /**\n{0}\n     */'.format('\n'.join(map(lambda j: '     * @param {1} element {0}'.format(1+j, ARG_NAME(j)), range(i))))
        class_code         += '\n    public {0}({1}) {{{2}}}'.format(CLASS_NAME(i), ', '.join(map(ARG, range(i))), ' '.join(map(lambda j: 'this.{0} = {0};'.format(ARG_NAME(j)), range(i)))) + '\n'
        class_code         += '\n    @Override public Iterable<?> data() {{ return I({0}); }}'.format(', '.join(map(ARG_NAME, range(i))))
#        class_code         += '\n    @Override public int     hashCode() {{Integer hash = 29; {0} return hash;}}'.format(' '.join(map(lambda j: 'hash += 37*hash + java.util.Objects.hashCode(this.{0});'.format(ARG_NAME(j)), range(i))))
#        class_code         += '\n    @Override public boolean equals  (Object obj) {{if (this == obj) return true; if (obj == null) return false; if (getClass() != obj.getClass()) return false; final {0} other = ({0}) obj;'.format(CLASS_TYPE(i)) + ' '.join(map(lambda j: 'if (!java.util.Objects.equals(this.{0}, other.{0})) return false;'.format(ARG_NAME(j)), range(i))) + 'return true;}'
#        class_code         += '\n    @Override public String  toString() {{return "{0}({1})".formatted({2});}}'.format(CLASS_NAME(i), ', '.join(['%s']*i), ', '.join(map(lambda j: 'this.{0}'.format(ARG_NAME(j)), range(i))))
        class_code         += '\n}'
        with open('{d}\\{n}'.format(d=VARIADIC_CLASSES_DIR, n='{0}.java'.format(CLASS_NAME(i))), 'w') as fd:

            fd.write(class_code)

        static_code       += '    /**\n     * tuple of {0} elements\n{1}\n     * @return tuple\n     */\n'.format(i, '\n'.join(map(lambda j: '     * @param <{1}> type {0}\n     * @param {2} element {0}'.format(1+j, ARG_TYPE(j), ARG_NAME(j)), range(i))))
        static_code       += '    public static {0} {1} T({2}) {{ return new {3}( {4}); }}\n'.format(CLASS_GENERICS(i), CLASS_TYPE(i), ', '.join(map(ARG, range(i))), CLASS_NAME(i)+('<>' if i > 0 else ''), ', '.join(map(ARG_NAME, range(i))))

#    with open('{d}\\{fn}'.format(d=VARIADIC_CLASSES_DIR, fn='_Static.java'), 'w') as fd:

#        fd.write(static_code)
        
    # TO-DO: embed pp(java) source in SuperPowers to generate helper static methods

if __name__ == '__main__':

    import argparse
    p = argparse.ArgumentParser(description='Generate Java Tuple classes for various cases - poor man\'s variadic tuples\nThis devtool will be replaced and removed soon.')
    p.parse_args()
    main()