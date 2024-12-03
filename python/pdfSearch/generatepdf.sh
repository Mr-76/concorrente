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
        SUBDIR="$PARENT_DIR/subdir$j"
        mkdir -p "$SUBDIR"
        
        # Generate 100 empty PDF files in each subdirectory
        for k in {1..1000}; do
            touch "$SUBDIR/file$k.pdf"
        done
    done
done

echo "Directory structure with empty PDF files created successfully in $BASE_DIR."

