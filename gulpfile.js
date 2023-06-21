var gulp = require('gulp');
var webpack = require('webpack-stream');
var merge = require('merge2');
const replace = require('gulp-replace');
var clean = require('gulp-clean');
var argv = require('yargs').argv;
var fs = require('fs');

gulp.task('drop-cache', function () {
    return gulp.src(['./src/main/resources/public/dist'], { read: false })
        .pipe(clean());
});

gulp.task('copy-mdi-font', ['drop-cache'], () => {
    var streams = [];
    streams
        .push(gulp.src('./node_modules/@mdi/font/fonts/*')
            .pipe(gulp.dest('./src/main/resources/public/mdi')))
    return merge(streams);
});

gulp.task('webpack', ['copy-mdi-font'], () => {
    var streams = [];
    streams.push(gulp.src('./src/main/resources/public/**/*.ts')
        .pipe(webpack(require('./webpack.config.js')))
        .on('error', function handleError() {
            this.emit('end'); // Recover from errors
        })
        .pipe(gulp.dest('./src/main/resources/public/dist')))
    return merge(streams);
});

gulp.task('build', ['webpack'], () => {
    var refs = gulp.src("./src/main/resources/view-src/**/*.html")
        .pipe(replace('@@VERSION', Date.now()))
        .pipe(gulp.dest("./src/main/resources/view"));

    var copyBehaviours = gulp.src('./src/main/resources/public/dist/behaviours.js')
        .pipe(gulp.dest('./src/main/resources/public/js'));

    return merge[refs, copyBehaviours];
});