import typing

from _util import *

N           :int    = 20
## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- 
CLASS_NAME_B:str    = 'Function'
ARG_TYPE_B  :str    = 'A'
ARG_NAME_B  :str    = 'a'
RE_TYPE     :str    = 'R'
METHOD_NAME :str    = 'apply'
METHOD_NAMES_DEPRECATED:typing.Iterable[str] = ('call',)
SUPERPOWER  :str    = 'function'
## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- ## -- 
import typing
ARG_TYPE        :typing.Callable[[int],str] = lambda i: '{0}{1}'  .format(ARG_TYPE_B, 1+i)
ARG_NAME        :typing.Callable[[int],str] = lambda i: '{0}{1}'  .format(ARG_NAME_B, 1+i)
ARG             :typing.Callable[[int],str] = lambda i: '{0} {1}' .format(ARG_TYPE(i), ARG_NAME(i))
CLASS_NAME      :typing.Callable[[int],str] = lambda n: '{0}{1}'  .format(CLASS_NAME_B, n)
CLASS_GENERICS  :typing.Callable[[int],str] = lambda n: '<{0}{1}>'.format(RE_TYPE, ''.join(map(lambda j: ', '+ARG_TYPE(j), range(n))))
CLASS_TYPE      :typing.Callable[[int],str] = lambda n: '{0}{1}'  .format(CLASS_NAME(n), CLASS_GENERICS(n))
CLASS_NAME_FULL :typing.Callable[[int],str] = lambda n: '{0}.{1}' .format(VARIADIC_CLASSES_JAVA_PACKAGE, CLASS_TYPE(n))

def main():

    import os
    import textwrap
    
    os.makedirs(VARIADIC_CLASSES_DIR, exist_ok=True)
    static_code:str = ''
    for i in range(1+N):

        class_code:str          = '\n'.join((

            'package {0};'.format(VARIADIC_CLASSES_JAVA_PACKAGE),
            '',
            '/**',
            ' * An interface for a function that takes {n} arguments. <br/>'.format(n=i),
            ' * ' + '\n * '.join('@param <{t}> type of argument nr {j}'.format(j=1+j, t=ARG_TYPE(j)) for j in range(i)),
            ' * @param <{0}> type of return'.format(RE_TYPE),
            ' */',
            'public interface {0} {{\n'.format(CLASS_TYPE(i)),
            '',
            textwrap.indent('\n'.join((
               
                '/**',
                ' * interface method',
                ' * '+'\n * '.join('@param {a} argument nr {j}'.format(j=1+j, a=ARG_NAME(j)) for j in range(i)),
                ' * @return whatever',
                ' */',
                'public {ret} {method}({args});'.format(method=METHOD_NAME, args=', '.join(map(ARG, range(i))), ret=RE_TYPE),

            )), 4*' '),
            *(textwrap.indent('\n'.join((
               
                '/**',
                ' * interface method',
                ' * DEPRECATED - use '+METHOD_NAME+'(), instead',
                ' * '+'\n * '.join('@param {a} argument nr {j}'.format(j=1+j, a=ARG_NAME(j)) for j in range(i)),
                ' * @return whatever',
                ' */',
                '@Deprecated',
                f'default public {RE_TYPE} {METHOD_NAME_DEPRECATED}({', '.join(map(ARG, range(i)))}) {{ return {METHOD_NAME}({','.join(map(ARG_NAME, range(i)))});}}',

            )), 4*' ') for METHOD_NAME_DEPRECATED in METHOD_NAMES_DEPRECATED),
            '}',
        
        ))
        with open('{d}\\{fn}'.format(d=VARIADIC_CLASSES_DIR, fn='{0}.java'.format(CLASS_NAME(i))), 'w') as fd:

            fd.write(class_code)

        static_code            += textwrap.indent('\n'.join((

            '/**',
            ' * super-powered method to create an anonymous function with {n} arguments. '.format(n=i),
            ' * Example usage: {{@code var f = {sp}({ex})}}'.format(sp=SUPERPOWER, ex='() -> 42' if i == 0 else '(String a) -> a.equals("42")' if i == 1 else '(String a, Integer b) -> (Integer.valueOf(a).equals(b))' if i == 2 else '(String a, Integer b, ...) -> ...'),
            ' * @param f function',
            ' * '+'\n * '.join('@param <{t}> type of argument nr {j}'.format(j=1+j, t=ARG_TYPE(j)) for j in range(i)),
            ' * @param <{t}> type of return'.format(t=RE_TYPE),
            ' * @return anonymous function',
            ' */',
            'public static {ret} {cls} {method}({cls} f) {{ return f; }}'.format(ret=CLASS_GENERICS(i), cls=CLASS_NAME_FULL(i), method=SUPERPOWER),

        ))+'\n', 4*' ')

#    with open('{d}\\{fn}'.format(d=VARIADIC_CLASSES_DIR, fn='_Static.java'), 'w') as fd:

#        fd.write(static_code)
    
    # TO-DO: embed pp(java) source in SuperPowers to generate helper static methods
    
if __name__ == '__main__':

    import argparse
    p = argparse.ArgumentParser(description='Generate Java function interfaces for various cases - poor man\'s variadic functions')
    p.parse_args()
    main()