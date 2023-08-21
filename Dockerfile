FROM ubuntu:latest
LABEL authors="vtkie"

ENTRYPOINT ["top", "-b"]