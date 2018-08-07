##########################
RasterFrames Users' Manual
##########################

*RasterFrames™* brings the power of Spark DataFrames to geospatial
raster data, empowered by the map algebra and tile layer operations of
`GeoTrellis <https://geotrellis.io/>`__.

The source code can be found on GitHub at
`locationtech/rasterframes <https://github.com/locationtech/rasterframes>`__.

The underlying purpose of RasterFrames™ is to allow data scientists and
software developers to process and analyze geospatial-temporal raster
data with the same flexibility and ease as any other Spark Catalyst data
type. At its core is a user-defined type (UDT) called
``TileUDT``, which encodes a GeoTrellis ``Tile`` in a form the Spark
Catalyst engine can process. Furthermore, we extend the definition of a
DataFrame to encompass some additional invariants, allowing for
geospatial operations within and between RasterFrames to occur, while
still maintaining necessary geo-referencing constructs.

Languages
---------

The user manual comes in two parts: one for Python, the other for Scala. While 
most features are available in both langugages, sometimes they are expressed
in slightly different forms to be more idiomatic to the language. 

.. toctree::
   :maxdepth: 2

   Python/index
   Scala/index


Related Links
-------------

-  `Gitter Channel <https://gitter.im/s22s/raster-frames>`__  |Join the
   chat at https://gitter.im/s22s/raster-frames|
-  `API Documentation <latest/api/index.html>`__
-  `GitHub Repository <https://github.com/locationtech/rasterframes>`__
-  `Astraea, Inc. <http://www.astraea.earth/>`__ (the company behind
   RasterFrames)

.. |Join the chat at https://gitter.im/s22s/raster-frames| image:: https://badges.gitter.im/s22s/raster-frames.svg
   :target: https://gitter.im/s22s/raster-frames?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge
