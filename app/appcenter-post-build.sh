if [ "$AGENT_JOBSTATUS" == "Succeeded" ]; then
    if [ "$APPCENTER_BRANCH" == "master" ];
     then
        curl -L https://github.com/appknox/appknox-go/releases/download/1.1.0/appknox-`uname -s`-x86_64 > appknox && chmod +x appknox
        ./appknox upload $APPCENTER_OUTPUT_DIRECTORY/app-release.apk
        rm appknox
    else
        echo "Current branch is $APPCENTER_BRANCH"
    fi
fi
