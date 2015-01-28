#!/bin/sh
export ProjectPath=$(cd ../; pwd)
export TargetClassName="com.demo.liuyifeng.hellojni.MainActivity"

export SourceFile="${ProjectPath}/app/src/main/java"
export TargetPath="${ProjectPath}/myjni/src/main/jni"

cd "${SourceFile}"
javah -d ${TargetPath} -classpath "${SourceFile}" "${TargetClassName}"