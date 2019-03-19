# Encode Extension

## What is it?

This extension is for converting between lists of bytes and strings of different encodings.  This is particularly useful for, say, taking binary data and turning it into a base64 string that can be easily passed around, or reading base64 data, converting it to a list of bytes, and manipulating those bytes.

## Primitives

| Prim Name         | Arguments       | Behavior
| ----------------- | --------------- | --------
| `base64-to-bytes` | `base64String`  | Decode `base64String` from base64 encoding to a list of bytes.
| `bytes-to-base64` | `byteList`      | Convert `byteList` into a base64-encoded string.  Throws an error if `byteList` contains any values that are not numbers between -128 and 127.
| `bytes-to-string` | `byteList`      | Convert `byteList` into a UTF-8-encoded string.  Throws an error if `byteList` contains any values that are not numbers between -128 and 127.
| `string-to-bytes` | `string`        | Decode `string` to a list of UTF-8-encoded bytes.

## Usage

### Encoding a string as base64

`(encode:bytes-to-base64 (encode:string-to-bytes my-string))`

### Decoding base64 into a string

`(encode:bytes-to-string (encode:base64-to-bytes my-base64))`

## Building

Open it in SBT.  If you successfully run `package`, `encode.jar` is created.

## Terms of Use

[![CC0](http://i.creativecommons.org/p/zero/1.0/88x31.png)](http://creativecommons.org/publicdomain/zero/1.0/)

The NetLogo Encode extension is in the public domain.  To the extent possible under law, Uri Wilensky has waived all copyright and related or neighboring rights.
