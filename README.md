# Reproduce potential bug in bson-kotlinx:5.2.0

## Summary

The current state of the repository reproduces the faulty behavior.
To reproduce it, run `gradle test`. The failing test indicates the faulty behavior.
I'd expect the test to be green.

If you remove the `testImplementation(libs.kotlinx.datetime)` part in the [build.gradle.kts](lib/build.gradle.kts)
the test will turn green.

## Analysis

My suggestion is that the reason behind this behavior is the `kotlinx.datetime`-support.
It has been integrated with https://github.com/mongodb/mongo-java-driver/pull/1462/files.

The error occurs when an old version of `kotlinx.datetime` is present in the class-path.
As you can see for tests to turn failing, being in the test classpath is enough to reproduce it.

## Why this is a problem?

3rd-party libraries one might be using can be behind with their (internal) usage of `kotlinx.datetime` and use old
versions. One example for this is the [kotest-property-arbs](https://github.com/kotest/kotest-property-arbs) library.
In the particular case above this is a library one most possibly is only adding for test-reasons only. There would be no
need to have support for serializing `kotlinx.datetime`-types to `BSON`.

## Possible solution

One possible solution would be to decouple the non-standard-library integrations for `bson-kotlinx` in separate modules.
This way developers can decide by themselves if they want to pull in support for a specific extension or not.
A possible naming-scheme could be `bson-kotlinx-datetime` for the `kotlinx-datetime` support.
