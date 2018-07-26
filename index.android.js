'use strict'

import React, { Component } from 'react';
import { NativeModules } from 'react-native'

const ComunicationApps = {};

ComunicationApps.setup = (name = '', packagesName = '', title = '', thumbnail = '', background = '', progress = 0, tags = '', lastViewVideos = '', isAudio = 0, isVideo = 0) =>{
    NativeModules.ComunicationApps.setup(
        name, packagesName, title, thumbnail, '', progress, tags, lastViewVideos, isAudio, isVideo
    );
    NativeModules.ComunicationApps.setBackground(background);
}

ComunicationApps.getInformations = () => {
    NativeModules.ComunicationApps.getInformations();
}

ComunicationApps.update = (name, packagesName, title, progress, tags, lastViewVideos, isAudio, isVideo) => {
    NativeModules.ComunicationApps.update(name, packagesName, title, progress, tags, lastViewVideos, isAudio, isVideo);
}

ComunicationApps.setBackground = (background) => {
    NativeModules.ComunicationApps.setBackground(background);
}

ComunicationApps.setProgress = (progress) => {
    NativeModules.ComunicationApps.setProgress(progress);
}

ComunicationApps.setLastViewVideos = (lastViewTime) => {
    NativeModules.ComunicationApps.setLastViewVideos(lastViewTime);
}

export default ComunicationApps;