#!/bin/bash

echo "Creating zip files..."
if zip -r -j percolation.zip src/*.java  ; then
	echo "Finished creating zip!"
else
	echo "Error creating zip"
fi

