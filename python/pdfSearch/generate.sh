#!/bin/bash

# Base directory where the structure will be created
BASE_DIR="base_directory"

# Create the base directory
mkdir -p "$BASE_DIR"

# Generate 4 directories, each with 4 subdirectories
for i in {1..4}; do
    PARENT_DIR="$BASE_DIR/dir$i"
    mkdir -p "$PARENT_DIR"
    for j in {1..4}; do
        mkdir -p "$PARENT_DIR/subdir$j"
    done
done

echo "Directory structure created successfully in $BASE_DIR."

