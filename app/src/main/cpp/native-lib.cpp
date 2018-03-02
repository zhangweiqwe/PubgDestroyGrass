#include <jni.h>
#include <string>
#include <fstream>
#include <android/log.h>


#define  TAG    "sssssss"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)

using namespace std;



/*


extern "C"
JNIEXPORT jstring

JNICALL
Java_cn_wsgwz_pubgmhd_myapplication_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject
) {


    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}



*/

extern "C"
JNIEXPORT void JNICALL
Java_com_vixkw_pubgmhd_MainActivity_grass(JNIEnv *env, jclass type) {
    char *lock_file = "sdcard/Android/data/com.tencent.tmgp.pubgmhd/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android/Engine.ini";

    ofstream fout( lock_file );
    if ( fout ) { // 如果创建成功
        fout << "" << endl; // 使用与cout同样的方式进行写入
        fout.close();  // 执行完操作后关闭文件句柄
    }

}

extern "C"
JNIEXPORT void JNICALL
Java_com_vixkw_pubgmhd_MainActivity_destroyGrass(JNIEnv *env, jclass type) {
    char *lock_file = "sdcard/Android/data/com.tencent.tmgp.pubgmhd/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android/Engine.ini";
    char *content = "[/script/engine.renderersettings]\n"
            "grass.MaxAsyncTasks=0";


    ofstream fout( lock_file );
    if ( fout ) { // 如果创建成功
        fout << content<< endl; // 使用与cout同样的方式进行写入
        fout.close();  // 执行完操作后关闭文件句柄
    }

}

