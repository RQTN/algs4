## Assignment1 - backwash in Percolation

> 作业网站：[Assignment1-Percolation](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php)，包含本次作业的说明，FAQ，相关的有用资源文件。

在作业网站的 [FAQ](https://coursera.cs.princeton.edu/algs4/assignments/percolation/faq.php) 中提到了一个叫做 backwash 的问题：

> **Q**: After the system has percolated, my `PercolationVisualizer` colors in light blue all sites connected to open sites on the bottom (in addition to those connected to open sites on the top). Is this “backwash” acceptable?
>
> **A**: No, this is likely a bug in `Percolation`. It is only a minor deduction (because it impacts only the visualizer and not the experiment to estimate the percolation threshold), so don’t go crazy trying to get this detail. However, many students consider this to be the most challenging and creative part of the assignment (especially if you limit yourself to one union–find object).
>
> <img src='../img/percolation-backwash.png'>



