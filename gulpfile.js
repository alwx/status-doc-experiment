var gulp = require('gulp');
var plumber = require('gulp-plumber');
var autoprefixer = require('gulp-autoprefixer');
var compass = require('gulp-compass');
var connect = require('gulp-connect');
var imagemin = require('gulp-imagemin');

gulp.task('connect', function() {
  connect.server({
    root: 'dest',
    livereload: true
  });
});

gulp.task('styles', function () {
  gulp.src(['src/scss/main.scss'])
    .pipe(plumber())
    .pipe(compass({
      sass: 'src/scss',
      css: 'dest/css',
      image: 'dest/images',
      sourcemap: true,
      debug : false
    }))
    .pipe(autoprefixer("last 3 versions", "> 1%"))
    .pipe(gulp.dest('dest/css'))
    .pipe(connect.reload());
});

gulp.task('html', function() {
  gulp.src('src/**/*.html')
    .pipe(gulp.dest('dest'))
    .pipe(connect.reload());
});

gulp.task('images', function() {
  gulp.src('src/images/*')
    .pipe(imagemin())
    .pipe(gulp.dest('dest/images'))
});

gulp.task('watch', function() {
  gulp.watch('src/scss/**/*.scss', ['styles']);
  gulp.watch('src/images/**/*', ['images']);
  gulp.watch('src/**/*.html', ['html']);
  gulp.watch('src/fonts/*', ['fonts']);
});

gulp.task('default', ['connect', 'styles', 'html', 'images', 'watch']);
