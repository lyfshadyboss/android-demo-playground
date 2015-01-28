#include "com_demo_liuyifeng_hellojni_MainActivity.h"

JNIEXPORT jstring JNICALL Java_com_demo_liuyifeng_hellojni_MainActivity_hellojni
  (JNIEnv* env, jobject obj)
{
    return env->NewStringUTF( "Hello from JNI !  Compiled with ABI .");
}
