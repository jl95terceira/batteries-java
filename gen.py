import os
import os.path
import pathlib

DIR            = pathlib.Path(__file__).parents[0]
GENERATORS_DIR = os.path.join(DIR, 'python-generators')

def main():

    d = os.getcwd()
    os.chdir(GENERATORS_DIR)
    try:
        for generator_name in (
            'ifexfunction',
            'ifexmethod',
            'iffunction',
            'ifmethod',
            'tupleclass'
        ):
            os.system(f'python {generator_name}.py')
    finally:
        os.chdir(d)

if __name__ == '__main__': main()
