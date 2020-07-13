# GPU장비설치

## OpenCV Compile

```bash
cmake3 -D CMAKE_BUILD_TYPE=RELEASE \
    -D CMAKE_INSTALL_PREFIX=/usr/local \
    -D WITH_CUDA=ON \
    -D ENABLE_FAST_MATH=1 \
    -D CUDA_FAST_MATH=1 \
    -D WITH_CUBLAS=1 \
    -D INSTALL_C_EXAMPLES=ON \
    -D INSTALL_PYTHON_EXAMPLES=ON \
    -D OPENCV_GENERATE_PKGCONFIG=ON \
    -D OPENCV_EXTRA_MODULES_PATH=/root/opencv_build/opencv_contrib/modules \
    -D BUILD_EXAMPLES=ON ..
```

### 참고자료

* NVIDIA Driver install
  * http://egloos.zum.com/muzie/v/7295846
  * https://linuxconfig.org/how-to-install-the-nvidia-drivers-on-centos-7-linux
  * https://www.linkedin.com/pulse/rhel7centos-nvidia-drviers-updated-christopher-meacham

* NVIDIA CUDA Toolkit install
  * https://developer.nvidia.com/cuda-downloads?target_os=Linux&target_arch=x86_64&target_distro=CentOS&target_version=7&target_type=rpmlocal

* OpenCV source compile
  * https://e3jake.tistory.com/38
  * https://www.pyimagesearch.com/2016/07/11/compiling-opencv-with-cuda-support/
  * https://webnautes.tistory.com/1030

* Pycharm remote interpreter 설정
  * https://pytogether.tistory.com/1
