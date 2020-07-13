# Python을 지탱하는 기술

## pip

pip는 파이썬으로 작성된 패키지 소프트웨어를 설치 · 관리하는 패키지 관리 시스템이다. Python Package Index (PyPI)에서 많은 파이썬 패키지를 볼 수 있다. 파이썬 2.7.9 이후 버전과 파이썬 3.4 이후 버전은 pip를 기본적으로 포함한다.

* 의존성 library 설치 시 활용
* requirements.txt 파일에 의존성 라이브러리 명시 및 설치 시 참조

```bash
pip install some-package-name
pip install -r requirements.txt  

pip uninstall some-package-name
```

## disutils

distutils 패키지는 파이썬 설치에 추가 모듈을 빌드하고 설치하는 것을 지원합니다. 새 모듈은 100% 순수 파이썬이거나 C로 작성된 확장 모듈일 수도 있고, 파이썬과 C로 코딩된 모듈을 포함하는 파이썬 패키지 모음일 수도 있습니다. 대부분 파이썬 사용자는 직접 이 모듈을 사용하려고 하지 않을겁니다, 대신 파이썬 패키징 위원회가 유지하는 교차 버전 도구를 사용합니다. 특히, setuptools는 다음을 제공하는 distutils의 향상된 대안입니다.

## setuptools

setuptools는 파이썬 표준 라이브러리 distutils를 개선하여 파이썬 프로젝트의 관리를 쉽게 하도록 설계된 패키지 개발 프로세스 라이브러리이다.

* 빌드 스크립트 작성
* 패키지 작업 시 활용
* cython 모듈로 빌드 시 활용

```bash
python setup.py build_ext --inplace
```

## venv

python을 위한 가상환경 셋팅 툴

### 참고자료

* [Python Tool recommendations](https://packaging.python.org/guides/tool-recommendations/)
* [venv](https://packaging.python.org/guides/installing-using-pip-and-virtual-environments/)
* [venv](https://docs.python.org/ko/3/tutorial/venv.html)

```python
# setup.py
from distutils.core import setup, Extension

from Cython.Build import cythonize

setup(
    name="i-ImageFilter",
    version='1.1',
    description='Image Filter Module made by ITNOMADS',
    author='lab_dev2',
    author_email='lab_dev2@itnomads.com',
    ext_modules=cythonize(
        [
            Extension(name="DEF", sources=["Config/DEF.py"]),
            Extension(name="ITNomads_Main_Module", sources=["proc/ITNomads_Main_Module.py"]),
            Extension(name="ITNomads_LicenseVerify", sources=["proc/ITNomads_LicenseVerify.py"]),
            Extension(name="ITNomads_Blind_Module", sources=["proc/ITNomads_Blind_Module.py"]),
            Extension(name="ITNomads_Classification_Module", sources=["proc/ITNomads_Classification_Module.py"]),
            Extension(name="ITNomads_OCR_Module", sources=["proc/ITNomads_OCR_Module.py"]),
            Extension(name="ITNomads_Pdf2Image_Module", sources=["proc/ITNomads_Pdf2Image_Module.py"]),
            Extension(name="UtilFunc", sources=["proc/UtilFunc.py"])
        ]
    )
)
```
