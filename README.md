# epub-translator 

[![Actions Status](https://github.com/sharplab/epub-translator/workflows/CI/badge.svg)](https://github.com/webauthn4j/webauthn4j-spring-security/actions)

epub-translator is an utility to translate epub books.

- Utilize DeepL API (You need to register DeepL API plan)
- Leave the original text for reference, and insert the translated text below per paragraph

## Build

```
./gradlew bootJar
```

## Configuration

place application.yml to `<epub-translator working directory>/config/application.yml`

#### application.yml

```
ePubTranslator:
  deepL:
    apiKey: <put your api key here>
  language:
    source: en        # default source language
    destination: ja   # default destination language
```

## Execution

```
java -jar epub-translator.jar --src <path to source epub file> [--dst <path to destination epub file>] \
[--srcLang <source language>] [--dstLang <destination language>]
```
