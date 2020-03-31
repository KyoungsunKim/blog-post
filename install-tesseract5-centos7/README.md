# CentOS 7 Tesseract-OCR 5.0.0 설치

CentOS 7 에서 최신버전 Tesseract-OCR 설치방법을 정리합니다. [공식홈페이지](https://github.com/tesseract-ocr/tesseract/wiki)에서 yum으로 설치하는 방법을 안내하고 있다.
하지만 설치되는 버전이 4.x라서 소스코드를 직접 다운받아 compile해서 설치하는 방법을 정리합니다. CentOS Linux release 7.7.1908 (Core) 환경에서 테스트 했습니다.

## 필수 Libray 설치

```bash
yum install -y autoconf automake libtool pkgconfig.x86_64
yum install -y libpng12-devel.x86_64 libjpeg-devel libtiff-devel.x86_64 zlib-devel.x86_64
yum install -y wget git
```

## GCC 최신버전 설치

centos7에 yum으로 설치하는 gcc를 사용할 경우 tesseract compile 과정에서 오류가 발생합니다. 최신버전의 gcc를 설치하여 사용합니다.

```bash
yum install centos-release-scl
yum install devtoolset-9-toolchain
scl enable devtoolset-9 bash
```

## Leptonica-1.79 설치

```bash
cd /root
wget http://www.leptonica.org/source/leptonica-1.79.0.tar.gz
tar -zxvf leptonica-1.79.0.tar.gz
cd leptonica-1.79.0
./configure --prefix=/usr/local/leptonica-1.79.0
make
make install
```

## Tesseract-OCR 5.0.0 설치

```bash
cd /root
export PKG_CONFIG_PATH=/usr/local/leptonica-1.79.0/lib/pkgconfig
git clone https://github.com/tesseract-ocr/tesseract.git
cd tesseract
./autogen.sh
./configure --prefix=/usr/local/tesseract-5.0 
make
make install
```

## traineddata 추가

[공식홈페이지](https://github.com/tesseract-ocr/tesseract/wiki)에서 다른 언어팩들을 다운받아서 사용할 수 있다.
```bash
cp -r ${file_upload_path}/kor.traineddata /usr/local/tesseract-5.0/share/tessdata
cp -r ${file_upload_path}/eng.traineddata /usr/local/tesseract-5.0/share/tessdata
```

## 환경변수 설정

```bash
# Set environment variable
export PATH=$PATH:/usr/local/tesseract-5.0/bin
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/local/tesseract-5.0/lib
export TESSDATA_PREFIX=/usr/local/tesseract-5.0/share/tessdata
```

## 모듈 실행 확인

-l 옵션을 활용하여 언어 설정을 변경할 수 있다. 결과파일은 지정한 이름에 자동으로 확장자가 .txt추가 되어 저장됩니다.

```bash
# Check version
tesseract -v
# Run Tesseract-OCR
tesseract -l eng $TEST_IMAGE $RESULT_FILE
```

### 참고자료

* [compiling-leptonica-on-centos-7](https://linuxcluster.wordpress.com/2020/02/03/compiling-leptonica-on-centos-7)