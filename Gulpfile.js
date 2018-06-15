const gulp = require('gulp');
const nunjucks = require('gulp-nunjucks');
const sass = require('gulp-sass');
const rename = require('gulp-rename');

gulp.task('html', () =>
    gulp.src(['src/njk/**/*.njk', '!src/njk/**/_*.njk'])
        .pipe(nunjucks.compile({}))
        .pipe(rename({extname: '.html'}))
        .pipe(gulp.dest('docs'))
);

gulp.task('sass', function () {
    return gulp.src('src/sass/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest('docs/css'));
});

gulp.task('img', () =>
    gulp.src('**/img/*.*', {cwd: 'src/njk'})
        .pipe(gulp.dest('docs'))
);

gulp.task('font', () =>
    gulp.src('node_modules/ionicons/dist/fonts/*')
        .pipe(gulp.dest('docs/fonts'))
);

gulp.task('watch', function () {
    gulp.watch('src/sass/*.scss', gulp.series('sass'));
    gulp.watch('src/njk/**/*.njk', gulp.series('html'));
    gulp.watch('src/njk/**/img/*.*', gulp.series('img'));
});

gulp.task('work', gulp.series('html', 'sass', 'img', 'font', 'watch'));