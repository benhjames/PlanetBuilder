# Setting up dependencies

NB: The files here are large and may be corrupted, so you may have to get them from Google Drive

Most IDEs will include JUnit (v4) under an option like `Add Library`

To set up JOGL (for OpenGL), I did the following in intelliJ. Unzip the folder (it may be easier to do this outside of the git repo, but it should work either way!) Then go to `File > Project Structure`, then click Libraries on the left hand side, and the plus. First select the root folder, but for some reason you will then need to repeat for the `jar` folder, and the `lib` folder inside! Should you be asked, the `lib` folder is a native library.

To add the Leap Motion library, add the `.jar` file as a library, and the `x86` and `x64` folders as `Native Library Locations`. IntelliJ worked it all out, but YMMV.

All should then work fine! - if not drop a message in the FB chat!
