'use strict'

import React, { Component } from 'react';
import { NativeModules } from 'react-native'

const ComunicationApps = {};

ComunicationApps.setup = (name = '', packagesName = '', title = '', progress = 0, tags = '', isAudio = 0, isVideo = 0) =>{
    NativeModules.ComunicationApps.setup(
        name, packagesName, title, progress, tags, isAudio, isVideo
    );
}

ComunicationApps.getInformations = () => {
    NativeModules.ComunicationApps.getInformations();
}

ComunicationApps.update = (name, packagesName, title, progress, tags, isAudio, isVideo) => {
    NativeModules.ComunicationApps.update(name, packagesName, title, progress, tags, isAudio, isVideo);
}

export default ComunicationApps;